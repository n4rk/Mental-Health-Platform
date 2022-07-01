import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user.model';
import { SecurityService } from 'src/app/services/security.service';
import { NotePad } from '../../model/notes.model';
import { NoteService } from '../../services/note.service';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  notes! : NotePad[];
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;

  constructor(private securityService:SecurityService, private noteService:NoteService, private router: Router) {
    this.userSub$ = this.securityService.userSubject.subscribe({
      next: user=>{
        this.user = user;
        this.userRole = user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
      },
      error: (err)=>{
        console.error(err)
      }
    })
  };

  ngOnInit(): void {
    this.securityService.getUser()
    this.user = this.securityService.user;
    this.userRole = this.securityService.user?.roles.find(e=>e.roleName=="ADMIN")!=undefined?"ADMIN":"USER";
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
