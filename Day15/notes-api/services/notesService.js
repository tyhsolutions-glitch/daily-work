const fs = require('fs').promises;

async function getNotes() {
    const data = await fs.readFile('./data/notes.json', 'utf-8')
    return JSON.parse(data);
}
async function getNoteById(id) {
    const notes = await getNotes();
    const found = notes.find((note)=> note.id === id);
    return found;
}
module.exports = { getNotes, getNoteById };