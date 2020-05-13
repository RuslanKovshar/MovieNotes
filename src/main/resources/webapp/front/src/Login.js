import React from "react";
import Api from "./Api";
import {Redirect} from "react-router";

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
                        <div className='container mt-5'>Login page
                            <form onSubmit={this.loginSubmitHandler}>
                                <input type="text"
                                       name='username'
                                       onChange={this.changeHandler}
                                       value={this.state.username}/>
                                <input type="password"
                                       name='password'
                                       onChange={this.changeHandler}
                                       value={this.state.password}/>
                                <button type='submit'>Login</button>
                            </form>
                        </div>
                }
            </div>
        );
    }
}

export default Login