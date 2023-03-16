import { NgModule } from "@angular/core";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatButtonModule } from "@angular/material/button";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "../app-routing.module";

@NgModule({
  imports: [
    MatButtonModule,
    MatToolbarModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    AppRoutingModule,
    // NgModule
  ],

  exports: [MatButtonModule, MatToolbarModule],
})
export class LoginModuleModule {}
