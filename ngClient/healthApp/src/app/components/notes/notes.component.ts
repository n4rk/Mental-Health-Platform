import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotePad } from '../../model/notes.model';
import { NoteService } from '../../services/note.service';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  notes! : NotePad[];

  constructor(private noteService:NoteService, private router: Router) {};

  ngOnInit(): void {
    this.getAllNotes();
  }

  private getAllNotes() {
    this.noteService.getAllNotes().subscribe(data => {
        this.notes = data;
      }, error => console.log(error)
    );
  }

  getNote(id: number){
    this.router.navigate(['notes', id]);
  }
  
  modifyNote(id: number){
    this.router.navigate(['notes','update', id]);
  }
  
  deleteNote(id: number) {
    this.noteService.deleteNote(id)
      .subscribe(
        data => {
          console.log(data);
          this.getAllNotes();
        },
        error => console.log(error));
  }

}
