import { Component, OnInit } from '@angular/core';
import { Note } from '../note';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-page-data',
  templateUrl: './page-data.component.html',
  styleUrls: ['./page-data.component.css']
})
export class PageDataComponent implements OnInit {
  [x: string]: any;
note: Note[] | undefined;
  currentUser: any;
  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

  // addNote () {
  //   this.notes.push({ id: this.notes.length + 1,content:'' });
  //   // sort the array
  //   this.notes= this.notes.sort((a: { id: number; },b: { id: number; })=>{ return b.id-a.id});
  //   localStorage.setItem('notes', JSON.stringify(this.notes));
  // };
  
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
  //    const id = event.srcElement.parentElement.parentElement.getAttribute('id');
  //    this.notes.forEach((note: { id: any; }, index: any)=>{
  //     if(note.id== id) {
  //       this.notes.splice(index,1);
  //       localStorage.setItem('notes', JSON.stringify(this.notes));
  //       console.log("********* deleting note *********")
  //       return;
  //     }
  //   });
  // }

}
