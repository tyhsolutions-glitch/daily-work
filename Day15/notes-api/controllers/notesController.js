const service = require('../services/notesService')

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