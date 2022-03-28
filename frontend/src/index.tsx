import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import MainContainer from './components/main';

ReactDOM.render(
  <React.StrictMode>
    <App />
    <MainContainer />
    
  </React.StrictMode>,
  document.getElementById('root')
);

