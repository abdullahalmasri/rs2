import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Note } from './note';
import { NoteServices } from './note.services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public notes: Note[] | undefined;
  public data: any[] | undefined;
  
  pageSize = 10;
  pageIndex = 0;
  

  constructor(private noteService:NoteServices){}

  ngOnInit(){
    this.getLogin();
    // this.getNotes();
  }
  public getLogin():any{
    // console.log(th);
    this.noteService.getLogin().subscribe(
        (da:any)=>{
          console.log("this is login ",da);
          this.data =da;

        }
    )
  }

  public getNotes():void{
    console.log("page ",this.pageIndex);
    console.log("size ",this.pageSize);

    this.noteService.getNotes(this.pageIndex,this.pageSize).subscribe(
      (response: Note[])=>{
        console.log('the data is ',response)
        this.notes = response;
      },(error:HttpErrorResponse)=>{
        console.log("error ", error);
      }
    )
  }
}
