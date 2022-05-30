import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotePad } from 'src/app/model/notes.model';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-create-note',
  templateUrl: './create-note.component.html',
  styleUrls: ['./create-note.component.css']
})
export class CreateNoteComponent implements OnInit {
  note : NotePad = new NotePad();

  constructor(private noteService: NoteService, private router: Router) { }

  ngOnInit(): void {
  }

  save() {
    this.noteService.createNote(this.note).subscribe(data => {
      console.log(data);
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    console.log(this.note);
    this.save();
  }

  gotoList() {
    this.router.navigate(['/notes']);
  }

}
