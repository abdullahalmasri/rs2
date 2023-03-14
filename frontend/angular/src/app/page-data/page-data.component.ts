import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, Subscriber } from 'rxjs';
import { Note } from '../note';
import { NoteServices } from '../note.services';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-page-data',
  templateUrl: './page-data.component.html',
  styleUrls: ['./page-data.component.css']
})
export class PageDataComponent implements OnInit {
  // [x: string]: any;
// notes: Note[] | undefined;
  currentUser: any;
  entityListFormGroup: FormGroup 

  // public notes: Note[];
  public data: any[] | undefined;
  
  pageSize = 10;
  pageIndex = 0;
  notes: Array<Note> = [];
  constructor(private token: TokenStorageService,private noteService:NoteServices,private fb: FormBuilder) { 
    this.entityListFormGroup = this.fb.group({
      notes:this.notes
    });
    // this.notes = this.notes;
  }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    // console.log(this.getNote());
    this.getNote()
    console.log('me',this.entityListFormGroup);

  }

  

  addNote () {
    this.notes.push({
      content: '',
      id: 0,
      title: '',
      label: []
    });
    // sort the array
    this.notes= this.notes.sort((a,b)=>{ return b.id-a.id});
    // localStorage.setItem('notes', JSON.stringify(this.notes));
  };
  
  // saveNote(event: { srcElement: { parentElement: { parentElement: { getAttribute: (arg0: string) => any; }; }; }; target: { innerText: any; }; }){
  //   const id = event.srcElement.parentElement.parentElement.getAttribute('id');
  //   const content = event.target.innerText;
  //   event.target.innerText = content;
  //   const json = {
  //     'id':id,
  //     'content':content
  //   }
  //   this.updateNote(json);
  //   localStorage.setItem('notes', JSON.stringify(this.notes));
  //   console.log("********* updating note *********")
  // }
  
  // updateNote(newValue: { id: any; content: any; }){
  //   this.notes.forEach((note: { id: any; }, index: string | number)=>{
  //     if(note.id== newValue.id) {
  //       this.notes[index].content = newValue.content;
  //     }
  //   });
  // }
  
  // deleteNote(event: { srcElement: { parentElement: { parentElement: { getAttribute: (arg0: string) => any; }; }; }; }){
  //    /const id = event.srcElement.parentElement.parentElement.getAttribute('id');
  //    this.notes.forEach((note: { id: any; }, index: any)=>{
  //     if(note.id== id) {
  //       this.notes.splice(index,1);
  //       localStorage.setItem('notes', JSON.stringify(this.notes));
  //       console.log("********* deleting note *********")
  //       return;
  //     }
  //   });
  // }

    getNote():void{
      
    // console.log("page ",this.pageIndex);
    // console.log("size ",this.pageSize);
    this.noteService.getNotes(this.pageIndex,this.pageSize).subscribe(res=>{
      this.notes=res;
      this.entityListFormGroup.get('notes')?.setValue(this.notes);

    })
    // console.log(note);
    // return note;
  }

}
