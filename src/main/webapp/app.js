const path = require('path');

const express = require('express');
const app = new express();
const port = process.env.PORT || 3000;

app.use(express.static('public'));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '', 'index.html'));
});
app.get('/login', (req, res) => {
  res.sendFile(path.join(__dirname, 'viws', 'login.html'));
});
app.get('/noPrivilege', (req, res) => {
  res.sendFile(path.join(__dirname, '', 'noPrivilege.html'));
});
app.post('/test/qrcode/:name', (req, res) => {
  const jsonFileName = req.params.name;
  const jsonFilePath = './public/textjson/' + jsonFileName + '.json';
  res.send(require(jsonFilePath));
});
app.post('/loginURL', (req, res) => {
  res.redirect('/');
});
app.listen(port, (error) => {
  if (error) {
    console.error(error);
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', port, port);
  }
});
