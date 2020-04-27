import React from "react";
import NoteItem from "./NoteItem";
import AddNote from "./AddNote";
import Api from "../Api";


class NotesList extends React.Component {
    token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGYWJ1bG9TIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU4NzY2MTYxOSwiZXhwIjoxNTg3NjY1MjE5fQ.mZweNSAH9wYIZLNwItw6Z_mW4w46ilkREY6Wzz3k5AA';
    constructor(props) {
        super(props);
        this.state = {
            notesList: []
        }
        this.handleNoteUpdate = this.handleNoteUpdate.bind(this);
        this.handleNoteDelete = this.handleNoteDelete.bind(this);
        this.handleNoteAdd = this.handleNoteAdd.bind(this);
        this.sort = this.sort.bind(this);
    }


    componentDidMount() {
        Api.get(`api/movie_notes/get`, {
                params: {},
                headers: {'Authorization': this.token}
            }
        ).then(res => {
            const noteList = res.data;
            noteList.map(note => note.releaseDate = new Date(note.releaseDate))
            console.log(res.data);
            this.sort(noteList);
            this.setState({notesList: noteList});
        }).catch(error => console.log(error));
    }

    handleNoteAdd(note) {
        Api.post(`api/movie_notes/add`,
            {
                releaseDate: note.releaseDate,
                title: note.title
            },
            {
                params: {},
                headers: {'Authorization': this.token}
            }
        ).then(res => {
            console.log(res.data);
            note.id = res.data.movie_note_id;
            let notesList = this.state.notesList;
            notesList.push(note);
            this.sort(notesList);
            this.setState({notesList: []});
            this.setState({notesList: notesList});
        })
    }

    handleNoteDelete(note) {
        Api.delete(`api/movie_notes/delete/` + note.id,
            {
                params: {},
                headers: {'Authorization': this.token}
            }
        ).then(() => {
            let notesList = this.state.notesList;
            let filter = notesList.filter((oldNote) => oldNote.id !== note.id);
            this.sort(filter);
            this.setState({notesList: []});
            this.setState({notesList: filter});
        })

    }

    handleNoteUpdate(note) {
        Api.put(`api/movie_notes/update/` + note.id,
            {
                releaseDate: note.releaseDate,
                title: note.title
            },
            {
                params: {},
                headers: {'Authorization': this.token}
            }
        ).then(res => {
            console.log(res.data);

            let notesList = this.state.notesList;
            let filter = notesList.filter((oldNote) => oldNote.id === note.id);
            filter[0].title = note.title;
            filter[0].releaseDate = note.releaseDate;

            this.sort(notesList);
            this.setState({notesList: []});
            this.setState({notesList: notesList});
        })
    }

    sort(notesList) {
        notesList.sort((a, b) => {
            if (a.releaseDate > b.releaseDate) return 1;
            if (a.releaseDate < b.releaseDate) return -1;
            return 0;
        });
    }

    render() {
        return (
            <div className='container'>
                <AddNote onNoteAdd={this.handleNoteAdd}/>
                <ul className='NoteList'>
                    {this.state.notesList.map((note, index) => {
                        return <NoteItem note={note}
                                         index={index}
                                         key={note.id}
                                         onNoteUpdate={this.handleNoteUpdate}
                                         onNoteDelete={this.handleNoteDelete}/>
                    })}
                </ul>
            </div>
        );
    }
}

export default NotesList;