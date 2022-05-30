import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostsComponent } from './components/posts/posts.component';
import { NotesComponent } from './components/notes/notes.component';
import { HttpClientModule } from '@angular/common/http';
import { CreatePostComponent } from './components/posts/create-post/create-post.component';
import { PostDetailsComponent } from './components/posts/post-details/post-details.component';
import { UpdatePostComponent } from './components/posts/update-post/update-post.component';
import { CreateNoteComponent } from './components/notes/create-note/create-note.component';
import { NoteDetailsComponent } from './components/notes/note-details/note-details.component';
import { UpdateNoteComponent } from './components/notes/update-note/update-note.component';
import { CreateCommentComponent } from './components/posts/post-details/create-comment/create-comment.component';
import { HomeComponent } from './components/home/home.component';
import { NewsComponent } from './components/news/news.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { NeedhelpComponent } from './components/needhelp/needhelp.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    NotesComponent,
    PostsComponent,
    CreatePostComponent,
    PostDetailsComponent,
    UpdatePostComponent,
    CreateNoteComponent,
    NoteDetailsComponent,
    UpdateNoteComponent,
    CreateCommentComponent,
    HomeComponent,
    NewsComponent,
    NotfoundComponent,
    AboutusComponent,
    NeedhelpComponent,
    ChatbotComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
