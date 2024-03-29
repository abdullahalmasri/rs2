import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoteServices } from './note.services';
import { LoginComponentComponent } from './login-component/login-component.component';
import { LoginModuleModule } from './login-module/login-module.module';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PageDataComponent } from './page-data/page-data.component';
import { NoteComponent } from './note/note.component';
import { ModalComponent } from './modal/modal.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    PageDataComponent,
    NoteComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModuleModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule
    ],

  providers: [NoteServices],
  bootstrap: [AppComponent],
  entryComponents:[
    ModalComponent
  ],
})
export class AppModule { }
