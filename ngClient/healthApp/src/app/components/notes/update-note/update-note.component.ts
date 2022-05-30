import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotePad } from 'src/app/model/notes.model';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-update-note',
  templateUrl: './update-note.component.html',
  styleUrls: ['./update-note.component.css']
})
export class UpdateNoteComponent implements OnInit {

  id!: number;
  note : NotePad = new NotePad();

  constructor(private noteService: NoteService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
    this.noteService.getNote(this.id).subscribe(data => {
        this.note = data;
        console.log(this.note);
      }, error => console.log(error));
  }

  onSubmit() {
    this.noteService.modifyNote(this.id, this.note).subscribe(data => {
      this.gotoList();
    }, error => console.log(error));  
  }

  gotoList() {
    this.router.navigate(['/notes']);
  }
}
