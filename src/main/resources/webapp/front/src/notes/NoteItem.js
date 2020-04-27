import React from "react";
import {AiFillCheckCircle, AiFillEdit} from "react-icons/ai";
import {TiDelete} from "react-icons/ti";

class NoteItem extends React.Component {
    note;

    constructor(props) {
        super(props);
        this.index = props.index;
        this.note = props.note;
        this.state = {
            isEditMode: false,
            note: {}
        }
        this.save = this.save.bind(this);
        this.edit = this.edit.bind(this);
        this.delete = this.delete.bind(this);
        this.daysCounter = this.daysCounter.bind(this);
        this.getDate = this.getDate.bind(this);
    }

    save() {
        this.props.onNoteUpdate(this.state.note)
        this.setState({
            note: '',
            isEditMode: false
        })
    }

    delete(note) {
        this.props.onNoteDelete(note);
    }

    edit(note) {
        this.setState({
            isEditMode: true,
            note: note
        })
    }

    onChange = (e) => {
        this.setState({
            note: {
                id: this.state.note.id,
                title: e.target.name === 'title' ? e.target.value : this.state.note.title,
                releaseDate: e.target.name === 'releaseDate' ? new Date(e.target.value) : this.state.note.releaseDate,
            }
        });
    }

    daysCounter() {
        let days = Math.ceil((this.note.releaseDate - new Date()) / (1000 * 60 * 60 * 24));
        if (days === 1) return 'tomorrow';
        if (days === 0) return 'today';
        if (days < 0) return 'released';
        return days + 'd';
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
        const isChange = this.state.isEditMode;
        let days = this.daysCounter();

        return (
            <li className={'noteItem ' + days}>
                <span>{this.index}</span>
                <span>
                    {
                        isChange
                            ? <input type='text'
                                     name='title'
                                     className='note-input'
                                     value={this.state.note.title}
                                     onChange={this.onChange}/>
                            : this.note.title
                    }
                </span>
                <span>
                {
                    isChange
                        ? <input type='date'
                                 className='note-input'
                                 name='releaseDate'
                                 value={this.getDate(this.state.note.releaseDate)}
                                 onChange={this.onChange}/>
                        : days
                }
                </span>
                <span>
                {
                    isChange
                        ? <button className='save-icon' onClick={() => this.save()}><AiFillCheckCircle/></button>
                        : <div>
                            <button className='edit-icon' onClick={() => this.edit(this.note)}><AiFillEdit/></button>
                            <button className='delete-icon' onClick={() => this.delete(this.note)}><TiDelete/></button>
                        </div>
                }
                </span>
            </li>
        )
    }
}

export default NoteItem;
