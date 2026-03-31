const service = require('../services/notesService')
exports.createNote = async (request, response)=>{
    const body = request.body;
    console.log(body);
    
  const notes = await service.getNotes();

//   const newNote = {
//     id: Date.now(),
//     title:,
//     content
//   };

//   notes.push(newNote);
  await service.save(notes);
  response.status(201).end();
}

exports.getAllNotes = async (request, response) =>{
    const notes = await service.getNotes();

    response.json(notes);
    // console.log(request.method);
    // console.log(request.headers);
}

exports.getNoteById= async (request, response) => {
    const note = await service.getNoteById(request.params.id);
    !note? response.status(404).end(): response.send(note);
  
}
