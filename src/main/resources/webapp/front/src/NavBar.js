import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import {createBrowserHistory} from "history";
import {Redirect} from "react-router";


class NavBar extends React.Component {
    customHistory;

    constructor(props) {
        super(props);
        this.state = {
            isRedirect: false
        }
    }

    componentDidMount() {
        this.customHistory = createBrowserHistory();
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
                            <Nav>
                                <Nav.Link href="/">Home</Nav.Link>
                            </Nav>
                            <Nav>
                                <Nav.Link href="/registration">Registration</Nav.Link>
                            </Nav>
                            <Nav>
                                <Nav.Link href="/login">Login</Nav.Link>
                            </Nav>

                            {
                                localStorage.getItem('token') !== null
                                    ? <form onSubmit={this.logoutHandler}>
                                        <button type='submit'>Logout</button>
                                    </form>
                                    : null
                            }
                        </Navbar>
                }
            </div>
        )
    }
}

export default NavBar;