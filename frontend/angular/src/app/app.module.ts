import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoteServices } from './note.services';
import { LoginComponentComponent } from './login-component/login-component.component';
import { LoginModuleModule } from './login-module/login-module.module';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { PageDataComponent } from './page-data/page-data.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    PageDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModuleModule,
    MatInputModule,
    ReactiveFormsModule
    ],

  providers: [NoteServices],
  bootstrap: [AppComponent]
})
export class AppModule { }
