import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import {Redirect} from "react-router";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";


class NavBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isRedirect: false
        }
    }


    logoutHandler = (e) => {
        e.preventDefault();
        localStorage.removeItem('token');
        this.setState({isRedirect: true});
    }

    render() {
        return (
            <div>
                {
                    this.state.isRedirect
                        ? <Redirect to='/login'/>
                        :
                        <Navbar bg="dark" variant="dark">
                            <Navbar.Brand href="/">RNotes</Navbar.Brand>
                            <Nav className="mr-auto">
                                <Nav.Link href="/">Home</Nav.Link>
                            </Nav>

                            {
                                localStorage.getItem('token') !== null
                                    ?
                                        <Form inline onSubmit={this.logoutHandler}>
                                            <Button variant="danger" type={"submit"}>Logout</Button>
                                        </Form>
                                    : null
                            }
                        </Navbar>
                }
            </div>
        )
    }
}

export default NavBar;