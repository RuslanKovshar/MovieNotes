import React from "react";
import Api from "./Api";
import {Redirect} from "react-router";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            isRedirect: false
        }
    }

    componentDidMount() {
        localStorage.setItem('test', 'value')
    }

    changeHandler = (e) => {
        let name = e.target.name;
        let value = e.target.value;
        this.setState({[name]: value});
    }

    loginSubmitHandler = (e) => {
        e.preventDefault()

        Api.post('api/auth', {
            username: this.state.username,
            password: this.state.password
        }).then(data => {
            console.log(data.data)
            let token = data.data.token;
            localStorage.setItem('token', token);

            this.setState({isRedirect: true})
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return (
            <div>
                {
                    this.state.isRedirect
                        ? <Redirect to='/'/>

                        :
                        <div className='container mt-5'>
                            <Form onSubmit={this.loginSubmitHandler}>
                                <Form.Group controlId={"formBasicUsername"}>
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control type='text'
                                                  className='note-input'
                                                  placeholder='Username'
                                                  name='username'
                                                  onChange={this.changeHandler}/>
                                    <Form.Control.Feedback type='invalid'>Must contain only digits, letters and . - _
                                        from 3 to 25
                                        symbols </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group controlId={"formBasicPassword"}>
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type='password'
                                                  className='note-input'
                                                  placeholder='Password'
                                                  name='password'
                                                  onChange={this.changeHandler}/>
                                    <Form.Control.Feedback type='invalid'>Must contain only digits, letters and . - _
                                        from 3 to 25
                                        symbols </Form.Control.Feedback>
                                </Form.Group>
                                <button id='submit-btn'
                                        className='note-add-btn'
                                        type='submit'>
                                    Login
                                </button>
                            </Form>
                        </div>
                }
            </div>
        );
    }
}

export default Login