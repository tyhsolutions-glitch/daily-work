const express = require('express');
const fs = require('fs');
const path = require('path');
const cors = require('cors');

const app = express();

app.use(cors());
app.use(express.json());

const dataPath = path.join(__dirname, 'data.json');
const responsePath = path.join(__dirname, 'response.json');

const readJSON = (filePath) => {
  try {
    if (!fs.existsSync(filePath)) {
      fs.writeFileSync(filePath, '[]'); 
    }
    const data = fs.readFileSync(filePath);
    return JSON.parse(data);
  } catch (err) {
    console.error('Read Error:', err);
    return [];
  }
};

const writeJSON = (filePath, data) => {
  try {
    fs.writeFileSync(filePath, JSON.stringify(data, null, 2));
  } catch (err) {
    console.error('Write Error:', err);
  }
};

app.get('/questions', (req, res) => {
  const data = readJSON(dataPath);
  res.json(data);
});

app.get('/responses', (req, res) => {
  const data = readJSON(responsePath);
  res.json(data);
});

app.post('/responses', (req, res) => {
  try {
    const { qid, answer } = req.body;
    const val = answer?.trim();

    if (!qid || val)
      return res.status(400).json({error:'Answer cannot be empty'});
    
    if (val.length < 5 || val.length > 25)
      return res.status(400).json({error:'Answer must be 5-25 characters'})

    const data = readJSON(responsePath);
    
    data.push({
      id:Date.now(),
      qid,
      answer:val
    });

    data.push(newEntry);
    writeJSON(responsePath, data);

    res.json({ message: 'Saved successfully' });
  } catch (err) {
    console.error('POST Error:', err);
    res.status(500).json({ error: 'Server error' });
  }
});

app.listen(3000, () => {
  console.log('Server running at http://localhost:3000');
});

module.exports = app; 
