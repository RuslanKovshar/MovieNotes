import React from "react";
import NoteItem from "./NoteItem";


class NotesList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            notesList: [
                {id: 1, title: "First note", releaseDate: "2020-04-22"},
                {id: 2, title: "Second note", releaseDate: "2020-04-23"},
                {id: 3, title: "Third note", releaseDate: "2020-06-02"}
            ]
        }
        this.handleNoteChange = this.handleNoteChange.bind(this);
    }

    handleNoteChange(note) {
        let notesList = this.state.notesList;
        let filter = notesList.filter((oldNote) => oldNote.id === note.id);
        filter[0].title = note.title
        filter[0].releaseDate = note.releaseDate
        this.setState(notesList)
    }

    render() {
        return (
            <ul className={"container NoteList"}>
                {this.state.notesList.map(note => {
                    return <NoteItem note={note} key={note.id} onNoteUpdate={this.handleNoteChange}/>
                })}
            </ul>
        );
    }
}

export default NotesList;