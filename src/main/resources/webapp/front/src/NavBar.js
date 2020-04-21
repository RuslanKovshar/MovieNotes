import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";


function NavBar() {
    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="#home">RNotes</Navbar.Brand>
            <Nav className="mr-auto">
                <Nav.Link href="#home">Home</Nav.Link>
            </Nav>
        </Navbar>
    )
}

export default NavBar;