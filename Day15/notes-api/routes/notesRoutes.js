const express = require('express');
const router = express.Router();

const controller = require('../controllers/notesController');
 router.get('/', controller.getAllNotes)
 router.get('/:id', controller.getNoteById)

 module.exports = router;