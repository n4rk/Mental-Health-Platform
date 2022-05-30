import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotePad } from 'src/app/model/notes.model';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-note-details',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.css']
})
export class NoteDetailsComponent implements OnInit {

  id!:number;
  note!:NotePad;

  constructor(private route: ActivatedRoute,private router: Router, private noteService: NoteService) { }

  ngOnInit(): void {
    this.note = new NotePad();
    this.id = this.route.snapshot.params['id'];
    
    this.noteService.getNote(this.id).subscribe(data => {
        console.log(data)
        this.note = data;
      }, error => console.log(error));
  }

  gotoList(){
    this.router.navigate(['notes']);
  }

}
