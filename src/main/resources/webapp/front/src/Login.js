import React from "react";
import Api from "./Api";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
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
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return (
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
        );
    }
}

export default Login