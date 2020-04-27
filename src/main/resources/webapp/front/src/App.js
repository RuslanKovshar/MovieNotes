import React from 'react';
import NavBar from "./NavBar";
import NotesList from "./notes/NotesList";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import Registration from "./Registration";

function App() {
    return (
        <Router>
            <div>
                <NavBar/>
                <Switch>
                    <Route exact path="/">
                        <NotesList/>
                    </Route>
                    <Route path="/registration">
                        <Registration/>
                    </Route>
                </Switch>
            </div>
        </Router>
    );
}

export default App;

