import React from 'react';
import NavBar from "./NavBar";
import NotesList from "./NotesList";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

function App() {
    return (
        <div className="App">
            <NavBar/>
            <NotesList/>
        </div>
    );
}

export default App;
