import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePostComponent } from './components/posts/create-post/create-post.component';
import { NotesComponent } from './components/notes/notes.component';
import { PostDetailsComponent } from './components/posts/post-details/post-details.component';
import { PostsComponent } from './components/posts/posts.component';
import { UpdatePostComponent } from './components/posts/update-post/update-post.component';
import { CreateNoteComponent } from './components/notes/create-note/create-note.component';
import { UpdateNoteComponent } from './components/notes/update-note/update-note.component';
import { NoteDetailsComponent } from './components/notes/note-details/note-details.component';
import { CreateCommentComponent } from './components/posts/post-details/create-comment/create-comment.component';
import { HomeComponent } from './components/home/home.component';
import { NewsComponent } from './components/news/news.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { NeedhelpComponent } from './components/needhelp/needhelp.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';
import { NotfoundComponent } from './components/notfound/notfound.component';

const routes: Routes = [
  
  { path: 'home', component: HomeComponent },
  { path: 'chatbot', component: ChatbotComponent },
  { path: 'news', component: NewsComponent },
  { path: 'posts', component: PostsComponent },
  { path: 'posts/add', component: CreatePostComponent },
  { path: 'posts/update/:id', component: UpdatePostComponent },
  { path: 'posts/:id', component: PostDetailsComponent },
  { path: 'posts/:id/add', component: CreateCommentComponent },
  { path: 'notes', component: NotesComponent },
  { path: 'notes/add', component: CreateNoteComponent },
  { path: 'notes/update/:id', component: UpdateNoteComponent },
  { path: 'notes/:id', component: NoteDetailsComponent },
  { path: 'needhelp', component: NeedhelpComponent },
  { path: 'aboutus', component: AboutusComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: '404', component: NotfoundComponent},
  { path: '**', redirectTo: '/404'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
