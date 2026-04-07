import { useState, useEffect } from "react";
import "./App.css";

const API = "http://localhost:3000";

export default function App() {
  const [p,setP] = useState("add"), [n,setN]=useState([]),
        [s,setS]=useState(false), [d,setD]=useState(null),
        [e,setE]=useState(""), [q,setQ]=useState(""),
        empty={title:"",description:"",startTime:"",priority:0,status:"Open"},
        [f,setF]=useState(empty);

  useEffect(()=>{ if(p==="notes") getNotes(); },[p]);

  const getNotes = async()=> setN(await (await fetch(`${API}/notes`)).json());

  const handleChange = e=>{
    let {name,value} = e.target;
    if(name==="title" && value.length>50) return;
    if(name==="description" && value.length>500) return;
    setF({...f,[name]:name==="priority"?+value:value});
  };

  const addNote = async()=>{
    if(!f.title.trim()) return setE("Title required");
    if(!f.description.trim()) return setE("Description required");
    await fetch(`${API}/notes`, {
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify({...f,status:"Open"})
    });
    await getNotes(); setF(empty); setS(true);
  };

  const deleteNote = async id => { await fetch(`${API}/notes/${id}`,{method:"DELETE"}); await getNotes(); setD(null); }

  const completeNote = async note => {
    const endTime = new Date().toISOString();
    await fetch(`${API}/notes/${note.id}`,{
      method:"PUT",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify({...note,status:"Closed and Completed",endTime})
    });
    await getNotes();
  };

  const sortNotes = ()=> setN([...n].sort((a,b)=>a.title.localeCompare(b.title)));

  return (
    <div>
      <nav className="navbar">
        <button className={p==="add"?"active":""} onClick={()=>setP("add")}>Add</button>
        <h2 className="logo">Notes App</h2>
        <button className={p==="notes"?"active":""} onClick={()=>setP("notes")}>Notes</button>
      </nav>

      {p==="add" &&
      <div className="card">
        <label>Title: {f.title.length}/50</label>
        <input name="title" value={f.title} onChange={handleChange}/>
        <label>Description: {f.description.length}/500</label>
        <textarea name="description" value={f.description} onChange={handleChange}/>
        <label>Priority: {f.priority}</label>
        <input type="range" name="priority" min="0" max="30" value={f.priority} onChange={handleChange}/>
        <label>Start:</label>
        <input type="datetime-local" name="startTime" value={f.startTime} onChange={handleChange}/>
        <button className="add-btn" onClick={addNote}>Add</button>
      </div>}

      {p==="notes" &&
      <>
        <div className="search-row">
          <input className="search" placeholder="🔍 Search..." value={q} onChange={e=>setQ(e.target.value)}/>
          <button className="sort-btn" onClick={sortNotes}>⟳ Sort</button>
        </div>

        {n.length?
        <table>
          <thead>
            <tr>
              <th>Title</th><th>Description</th><th>Priority</th>
              <th>Start</th><th>End</th><th>Status</th><th>Action</th>
            </tr>
          </thead>
          <tbody>
            {n.filter(x=>x.title.toLowerCase().includes(q.toLowerCase())||x.description.toLowerCase().includes(q.toLowerCase()))
              .map(x=>
                <tr key={x.id}>
                  <td>{x.title}</td>
                  <td>{x.description}</td>
                  <td>{x.priority??0}</td>
                  <td>{x.startTime?new Date(x.startTime).toLocaleString():"-"}</td>
                  <td>{x.endTime?new Date(x.endTime).toLocaleString():"-"}</td>
                  <td className={x.status.toLowerCase()}>{x.status}</td>
                  <td>
                    {x.status==="Open" && <button className="complete-btn" onClick={()=>completeNote(x)}>Mark as Completed</button>}
                    <button className="del-btn" onClick={()=>setD(x.id)}>Delete</button>
                  </td>
                </tr>)}
          </tbody>
        </table>
        : <p style={{textAlign:"center"}}>No notes</p>}
      </>}

      {s && <div className="modal"><div className="modal-box"><p>Note added ✅</p><button onClick={()=>setS(false)}>OK</button></div></div>}
      {e && <div className="modal"><div className="modal-box"><p>{e}</p><button onClick={()=>setE("")}>OK</button></div></div>}
      {d && <div className="modal"><div className="modal-box">
        <p>Delete?</p>
        <div className="modal-actions">
          <button className="yes" onClick={()=>deleteNote(d)}>Yes</button>
          <button className="no" onClick={()=>setD(null)}>No</button>
        </div>
      </div></div>}
    </div>
  );
}
