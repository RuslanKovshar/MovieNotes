import React from "react";
import {AiFillEdit} from "react-icons/ai";
import {AiFillCheckCircle} from "react-icons/ai";

class NoteItem extends React.Component {
    note;

    constructor(props) {
        super(props);
        this.note = props.note;
        this.state = {
            isEditMode: false,
            note: {}
        }
        this.save = this.save.bind(this);
        this.edit = this.edit.bind(this);
    }

    save() {
        this.props.onNoteUpdate(this.state.note)
        this.setState({
            note: '',
            isEditMode: false
        })
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
                releaseDate: e.target.name === 'releaseDate' ? e.target.value : this.state.note.releaseDate,
            }
        });
    }

    render() {
        const isChange = this.state.isEditMode;

        let number = (Date.parse(this.note.releaseDate) - new Date()) / (1000 * 60 * 60 * 24);
        let floor = Math.ceil(number);
        return (
            <li className={"noteItem"}>
                <span>
            {this.note.id}
            </span>
                <span>
                    {
                        isChange ?
                            <input type='text'
                                   className='form-control'
                                   name="title"
                                   value={this.state.note.title}
                                   onChange={this.onChange}/>
                            : this.note.title
                    }
                </span>
                {
                    isChange
                        ? <input type='date'
                                 className='form-control'
                                 name='releaseDate'
                                 value={this.state.note.releaseDate}
                                 onChange={this.onChange}/>
                        : floor === 1 ? 'tomorrow' :  floor + ' d'
                }

                {
                    isChange
                        ? <button onClick={() => this.save()}><AiFillCheckCircle/></button>
                        : <button onClick={() => this.edit(this.note)}><AiFillEdit/></button>
                }


            </li>
        )
    }
}

export default NoteItem;


/*
class ListPersonaComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            persons: [],
            message: null,
            isEditMode: false,
            id: '',
            name: '',
            email: '',
            nationality: '',
        }
        this.deletePersona = this.deletePersona.bind(this);
        this.editPersona = this.editPersona.bind(this);
        this.addPersona = this.addPersona.bind(this);
        this.reloadPersonaList = this.reloadPersonaList.bind(this);
        this.savePersona = this.savePersona.bind(this);
        this.loadPersona = this.loadPersona.bind(this);
    }


    componentDidMount() {
        this.reloadPersonaList();
    }

    reloadPersonaList() {
        ApiService.fetchPersons()
            .then((res) => {
                this.setState({persons: res.data.result})
            });
    }

    deletePersona(personaId) {
        ApiService.deletePersona(personaId)
            .then(res => {
                this.setState({message: 'Persona deleted successfully.'});
                this.setState({persons: this.state.persons.filter(persona => persona.id !== personaId)});
            })

    }

    loadPersona() {
        ApiService.fetchPersonaById(window.localStorage.getItem("personaId"))
            .then((res) => {
                let persona = res.data.result;
                this.setState({
                    id: persona.id,
                    name: persona.name,
                    email: persona.email,
                    nationality: persona.nationality,
                })
            });
    }

    onChange = (e) =>
        this.setState({[e.target.name]: e.target.value});

    savePersona = (e) => {
        e.preventDefault();
        let persona = {
            id: this.state.id, name: this.state.name, email: this.state.email,
            nationality: this.state.nationality
        };
        ApiService.editPersona(persona)
            .then(res => {
                this.state.isEditMode = false;
                this.setState({message: 'Persona added successfully.'});

                this.props.history.push('/persona');
                this.reloadPersonaList();
            });
    }

    editPersona(id) {
        window.localStorage.setItem("personaId", id);
        this.state.isEditMode = true;
        this.loadPersona();
        // this.props.history.push('/edit-persona');
    }

    addPersona() {
        window.localStorage.removeItem("personaId");
        this.props.history.push('/add-persona');
    }

    backToMain() {
        this.state.isEditMode = false;
        this.reloadPersonaList();
        this.props.history.push('/persona');
    }

    render() {
        const isChange = this.state.isEditMode;
        return (
            <div className="container mt-4 col-12">
                <div className="card border-secondary">
                    <div className="card-header bg-secondary text-white">
                        <button className="btn btn-success" onClick={() => this.addPersona()}><i className="fa fa-plus"
                                                                                                 aria-hidden="true"></i>
                        </button>

                    </div>
                    <div className="card-body table-responsive-md">

                        <table className="table table-hover table-bordered">
                            <thead>
                            <tr className="bg-secondary text-white text-center">
                                <th className="d-none">Id</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Nationality</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.persons.map(
                                    persona =>
                                        <tr key={persona.id}>

                                            {isChange && window.localStorage.getItem("personaId") == persona.id ?
                                                <td><input type='text' className='form-control' name="name"
                                                           value={this.state.name}
                                                           onChange={this.onChange}/>
                                                </td> : <td>{persona.name}</td>}


                                            {isChange && window.localStorage.getItem("personaId") == persona.id ?
                                                <td><input type='text' className='form-control' name="email"
                                                           value={this.state.email}
                                                           onChange={this.onChange}/>
                                                </td> : <td>{persona.email}</td>}

                                            {isChange && window.localStorage.getItem("personaId") == persona.id ?
                                                <td><input type='text' className='form-control' name="nationality"
                                                           value={this.state.nationality}
                                                           onChange={this.onChange}/>
                                                </td> : <td>{persona.nationality}</td>}


                                            {isChange && window.localStorage.getItem("personaId") == persona.id ?
                                                <td className="text-center">

                                                    <button className="btn btn-success" onClick={this.savePersona}><i
                                                        className="fa fa-save"></i></button>
                                                    &nbsp;
                                                    <button className="btn btn-primary"
                                                            onClick={() => this.backToMain()}><i
                                                        className="fa fa-times" aria-hidden="true"></i></button>
                                                    &nbsp;
                                                </td> :
                                                <td className="text-center">

                                                    <button className="btn btn-md btn-warning"
                                                            onClick={() => this.editPersona(persona.id)}><i
                                                        className="fa fa-pencil" aria-hidden="true"></i>
                                                    </button>
                                                    &nbsp;
                                                    <button className="btn btn-md btn-danger"
                                                            onClick={() => this.deletePersona(persona.id)}><i
                                                        className="fa fa-scissors" aria-hidden="true"></i>
                                                    </button>
                                                    &nbsp;

                                                </td>
                                            }
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }

}

export default ListPersonaComponent;*/
