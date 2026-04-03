const fs = require('fs').promises;
const FILE = 'notes.json';

async function init() {
  try {
    await fs.access(FILE);
  } catch {
    await fs.writeFile(FILE, JSON.stringify([]));
  }
}

async function getNotes() {
  const data = await fs.readFile(FILE, 'utf8');
  return JSON.parse(data);
}

async function saveNotes(notes) {
  await fs.writeFile(FILE, JSON.stringify(notes, null, 2));
}
function isValid(input) {
  return input && input.trim() !== '';
}
async function addNote(title, content) {

  if (!isValid(title) || !isValid(content)) {
    return console.log(' Title and Content cannot be empty');
  }

  const notes = await getNotes();

  const newNote = {
    id: Date.now(),
    title: title.trim(),
    content: content.trim()
  };

  notes.push(newNote);
  await saveNotes(notes);

  console.log('Note added:', newNote);
}
async function listNotes() {
  const notes = await getNotes();

  if (notes.length === 0) {
    return console.log('📭 No notes found');
  }

  console.log('📒 Notes:');
  notes.forEach(n => {
    console.log(`- [${n.id}] ${n.title}: ${n.content}`);
  });
}
async function getNote(id) {
  if (!id) return console.log('❌ Please provide an ID');

  const notes = await getNotes();
  const note = notes.find(n => n.id == id);

  if (!note) return console.log('⚠️ Note not found');

  console.log('📌 Found:', note);
}
async function updateNote(id, newContent) {

  if (!id) return console.log('❌ Please provide an ID');
  if (!isValid(newContent)) {
    return console.log('❌ Content cannot be empty');
  }

  const notes = await getNotes();

  let found = false;

  const updated = notes.map(n => {
    if (n.id == id) {
      found = true;
      return { ...n, content: newContent.trim() };
    }
    return n;
  });

  if (!found) return console.log('⚠️ Note not found');

  await saveNotes(updated);
  console.log('✅ Note updated');
}

async function deleteNote(id) {

  if (!id) return console.log('❌ Please provide an ID');

  const notes = await getNotes();

  const filtered = notes.filter(n => n.id != id);

  if (filtered.length === notes.length) {
    return console.log('⚠️ Note not found');
  }

  await saveNotes(filtered);
  console.log('🗑️ Note deleted');
}

async function run() {
  await init();

  const [, , command, ...args] = process.argv;

  switch (command) {

    case 'add':
      if (!args[0] || !args[1]) {
        console.log('❌ Usage: node app.js add "Title" "Content"');
        break;
      }
      await addNote(args[0], args[1]);
      break;

    case 'list':
      await listNotes();
      break;

    case 'get':
      await getNote(args[0]);
      break;

    case 'update':
      if (!args[0] || !args[1]) {
        console.log('❌ Usage: node app.js update <id> "New Content"');
        break;
      }
      await updateNote(args[0], args[1]);
      break;

    case 'delete':
      await deleteNote(args[0]);
      break;

    default:
      console.log(`
Usage:
  node app.js add "Title" "Content"
  node app.js list
  node app.js get <id>
  node app.js update <id> "New Content"
  node app.js delete <id>
  `);
  }
}
run();