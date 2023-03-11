import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoteServices } from './note.services';
import { LoginComponentComponent } from './login-component/login-component.component';
import { LoginModuleModule } from './login-module/login-module.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModuleModule
    ],

  providers: [NoteServices],
  bootstrap: [AppComponent]
})
export class AppModule { }
