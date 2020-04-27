import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";


function NavBar() {
    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="/">RNotes</Navbar.Brand>
            <Nav>
                <Nav.Link href="/">Home</Nav.Link>
            </Nav>
            <Nav>
                <Nav.Link href="/registration">Registration</Nav.Link>
            </Nav>
        </Navbar>
    )
}

export default NavBar;