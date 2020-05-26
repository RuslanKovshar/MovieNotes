import React from 'react';
import Form from 'react-bootstrap/Form';
import Api from "./Api";
import Alert from "react-bootstrap/Alert";

class Registration extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            email: '',
            usernameValid: null,
            emailValid: null,
            passwordValid: null,
            success: false,
            error: false
        }
    }

    changeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name]: value});

        if (name === 'username') {
            const usernameRegexp = /^[a-zA-Z0-9\\._-]{3,25}$/
            this.setState({usernameValid: usernameRegexp.test(value)})
        }

        if (name === 'email') {
            const emailRegexp = /^[a-zA-Z0-9\\.-_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/
            this.setState({emailValid: emailRegexp.test(value)})
        }

        if (name === 'password') {
            const passwordRegexp = /^[a-zA-Z0-9\\._-]{8,25}$/
            this.setState({passwordValid: passwordRegexp.test(value)})
        }
    }

    formSubmitHandler = (event) => {
        event.preventDefault()

        if (this.state.usernameValid && this.state.emailValid && this.state.passwordValid) {
            Api.post('api/register',
                {
                    username: this.state.username,
                    email: this.state.email,
                    password: this.state.password
                }
            ).then(res => {
                    console.log(res)
                    this.setState({
                        username: '',
                        password: '',
                        email: '',
                        usernameValid: null,
                        emailValid: null,
                        passwordValid: null,
                        success: true,
                        error: false
                    })
                }
            ).catch(() => {
                this.setState({
                    password: '',
                    usernameValid: false,
                    emailValid: false,
                    passwordValid: null,
                    success: false,
                    error: true
                })
            })
        }
    }

    render() {

        return (
            <div className='container mt-5'>
                {
                    this.state.success ? <Alert className='mt-5' variant='success'>New user created! Go to <a href={"/login"}>login page</a></Alert> :
                        <div/>
                }
                {
                    this.state.error ?
                        <Alert className='mt-5' variant='danger'>User with this username or email already
                            exist</Alert> : <div/>
                }
                <Form onSubmit={this.formSubmitHandler}>
                    <Form.Group controlId='formBasicUsername'>
                        <Form.Label>Username</Form.Label>
                        <Form.Control type='text'
                                      className='note-input'
                                      placeholder='Username'
                                      name='username'
                                      value={this.state.username}
                                      {
                                          ...this.state.usernameValid !== null
                                              ? {
                                                  isValid: this.state.usernameValid,
                                                  isInvalid: !this.state.usernameValid
                                              }
                                              : {}
                                      }
                                      onChange={this.changeHandler}/>
                        <Form.Control.Feedback type='invalid'>Must contain only digits, letters and . - _ from 3 to 25
                            symbols </Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId='formBasicEmail'>
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type='email'
                                      className='note-input'
                                      placeholder='Enter email'
                                      name='email'
                                      value={this.state.email}
                                      {
                                          ...this.state.emailValid !== null
                                              ? {
                                                  isValid: this.state.emailValid,
                                                  isInvalid: !this.state.emailValid
                                              }
                                              : {}
                                      }
                                      onChange={this.changeHandler}/>
                        <Form.Control.Feedback type='invalid'>Must contain only digits, letters and . -
                            _ </Form.Control.Feedback>
                        <Form.Text className='text-muted'>
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group controlId='formBasicPassword'>
                        <Form.Label>Password</Form.Label>
                        <Form.Control type='password'
                                      className='note-input'
                                      placeholder='Password'
                                      name='password'
                                      value={this.state.password}
                                      {
                                          ...this.state.passwordValid !== null
                                              ? {
                                                  isValid: this.state.passwordValid,
                                                  isInvalid: !this.state.passwordValid
                                              }
                                              : {}
                                      }
                                      onChange={this.changeHandler}/>
                        <Form.Control.Feedback type='invalid'>Must contain only digits, letters and . - _ from 8 to 25
                            symbols </Form.Control.Feedback>
                    </Form.Group>
                    <button id='submit-btn'
                            className='note-add-btn'
                            type='submit'>
                        Submit
                    </button>
                </Form>
            </div>
        );
    }
}


export default Registration;