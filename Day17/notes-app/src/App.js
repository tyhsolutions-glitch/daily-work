import { useState, useEffect } from "react";
import "./App.css";

const API = "http://localhost:8080/notes-api";

export default function App() {
  const [p,setP] = useState("add"),
        [n,setN]=useState([]),
        [s,setS]=useState(false),
        [d,setD]=useState(null),
        [e,setE]=useState(""),
        [q,setQ]=useState(""),
        empty={title:"",content:""},
        [f,setF]=useState(empty);

  useEffect(()=>{ if(p==="notes") getNotes(); },[p]);

  const getNotes = async()=> {
    const res = await fetch(API);
    setN(await res.json());
  };

  const handleChange = e=>{
    let {name,value} = e.target;
    setF({...f,[name]:value});
  };

  const addNote = async()=>{
    if(!f.title.trim()) return setE("Title required");
    if(!f.content.trim()) return setE("Content required");

    await fetch(API, {
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(f)
    });

    await getNotes();
    setF(empty);
    setS(true);
  };

  const deleteNote = async id => {
    await fetch(`${API}/${id}`,{method:"DELETE"});
    await getNotes();
    setD(null);
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
        <label>Title</label>
        <input name="title" value={f.title} onChange={handleChange}/>

        <label>Content</label>
        <textarea name="content" value={f.content} onChange={handleChange}/>

        <button className="add-btn" onClick={addNote}>Add</button>
      </div>}

      {p==="notes" &&
      <>
        <div className="search-row">
          <input className="search" placeholder="Search..."
            value={q} onChange={e=>setQ(e.target.value)}/>
          <button className="sort-btn" onClick={sortNotes}>Sort</button>
        </div>

        {n.length?
        <table>
    <thead>
  <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Content</th>
    <th>Status</th>
    <th>Date</th>
    <th>Action</th>
  </tr>
</thead>

<tbody>
  {n.filter(x =>
    x.title.toLowerCase().includes(q.toLowerCase()) ||
    x.content.toLowerCase().includes(q.toLowerCase())
  ).map(x => (
    <tr key={x.id}>
      <td>{x.id}</td>
      <td>{x.title}</td>
      <td>{x.content}</td>
      <td>{x.status}</td>
      <td>{x.createdAt}</td>

      <td>
        <button
          onClick={() => deleteNote(x.id)}
          style={{
            backgroundColor: "red",
            color: "white",
            border: "none",
            padding: "6px 10px",
            borderRadius: "5px",
            cursor: "pointer"
          }}
        >
          Delete
        </button>
      </td>
    </tr>
  ))}
</tbody>

        </table>
        : <p style={{textAlign:"center"}}>No notes</p>}
      </>}

      {s && <div className="modal"><div className="modal-box"><p>Note added ✅</p><button onClick={()=>setS(false)}>OK</button></div></div>}
      {e && <div className="modal"><div className="modal-box"><p>{e}</p><button onClick={()=>setE("")}>OK</button></div></div>}
    </div>
  );
}
