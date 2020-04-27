import React from "react";
import Alert from "react-bootstrap/Alert";

class AddNote extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            title: '',
            releaseDate: new Date(),
            error: ''
        }
    }

    changeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name]: value});
    }

    changeDateHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name]: new Date(value)});
    }

    handleSubmit = (e) => {
        e.preventDefault();

        let note = {
            title: this.state.title,
            releaseDate: this.state.releaseDate
        }

        if (note.title !== '') {
            this.setState({
                title: '',
                releaseDate: new Date(),
                error: ''
            })

            this.props.onNoteAdd(note);
        } else {
            this.setState({
                error: 'Please enter the title!'
            })
        }
    }

    getDate(date) {
        let stringDate = date.getDate().toString();
        let day = stringDate.length === 1 ? '0' + stringDate : stringDate;
        let stringMonth = (date.getMonth() + 1).toString();
        let month = stringMonth.length === 1 ? '0' + stringMonth : stringMonth;
        let year = date.getFullYear();
        return year + "-" + month + "-" + day;
    }

    render() {
        return (
            <div>
                {
                    this.state.error !== '' ? <Alert className='mt-5' variant='danger'>{this.state.error}</Alert> : <div/>
                }
                <form className='note-add' onSubmit={this.handleSubmit}>
                    <div className='row'>
                        <input type="text"
                               className='note-input col'
                               name='title'
                               value={this.state.title}
                               onChange={this.changeHandler}/>
                        <input type="date"
                               className='note-input col'
                               name='releaseDate'
                               value={this.getDate(this.state.releaseDate)}
                               onChange={this.changeDateHandler}/>
                    </div>
                    <div className='row'>
                        <button className='col note-add-btn' type='submit'>Add note</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default AddNote;