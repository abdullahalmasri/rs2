import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { Note } from "../note";
import { NoteServices } from "../note.services";
import { TokenStorageService } from "../_services/token-storage.service";
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from "../modal/modal.component";

@Component({
  selector: "app-page-data",
  templateUrl: "./page-data.component.html",
  styleUrls: ["./page-data.component.css"],
})
export class PageDataComponent implements OnInit {
  // [x: string]: any;
  // notes: Note[] | undefined;
  currentUser: any;
  entityListFormGroup: FormGroup;
  @Input() public note: Note | undefined;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  // public notes: Note[];
  public data: any[] | undefined;

  pageSize = 10;
  pageIndex = 0;
  notes: Array<Note> = [];
  closeResult: string = '';
  constructor(
    private token: TokenStorageService,
    private noteService: NoteServices,
    private fb: FormBuilder,
    private modalService: NgbModal
  ) {
    this.entityListFormGroup = this.fb.group({
      notes: [this.notes],
    });
    // this.notes = this.notes;
  }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    // console.log(this.getNote());
    this.getNote();
    console.log("me", this.entityListFormGroup);
  }

  addNote() {
    let note: Note;
    const notes = [];
    console.log("notes ", this.entityListFormGroup.value.notes.totalElements);
    note = {
      content: "",
      id: this.entityListFormGroup.value.notes.totalElements + 1,
      labels: [],
      title: "",
    };
    console.log(note);
    // this.notes.push(note);
    // if(Array.isArray(this.notes)){
    notes.push(note);

    // }else{
    // console.log("Given data is not an array")
    // }

    // sort the array
    // this.notes.push(note);

    // this.notes = this.notes.sort((a,b)=>{ return b.id-a.id});
    // localStorage.setItem('notes', JSON.stringify(notes));
  }
  // passBack() {
  //   this.passEntry.emit(this.note);
  // }
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
  
  getNote(): void {
    // console.log("page ",this.pageIndex);
    // console.log("size ",this.pageSize);
    this.noteService
      .getNotes(this.pageIndex, this.pageSize)
      .subscribe((res) => {
        this.notes = res;
        this.entityListFormGroup.get("notes")?.setValue(this.notes);
      });
    // console.log(note);
    // return note;
  }

  open() {
    const modalRef = this.modalService.open(ModalComponent,
      {
        // scrollable: true,
        // windowClass: 'myCustomModalClass',
        // keyboard: false,
        // backdrop: 'static'
      });

    this.note = {
      title: '',
      content: '',
      labels: []
    }

    modalRef.componentInstance.fromParent = this.note ;
    modalRef.result.then((result) => {
      console.log(result);
    }, (reason) => {
    });
  
  //  console.log('modal', this.modalService
      // .open(content,{ariaLabelledBy:"modal-body"}))
      // const modalRef = this.modalService.open(ModalComponent);
      // modalRef.componentInstance.user = this.user;
      // const modalRef=this.modalService
      // .open(ModalComponent)
      // modalRef.componentInstance.note = this.note;
      // modalRef.componentInstance.passEntry.subscribe(
      //   (receivedEntry: any) => 
      //   {console.log(receivedEntry);
      //   })
        // modalRef.result.then((result) => {if (result) {console.log(result);}});
      // .result.then(
      //   (result) => {
      //     console.log('result ',result);
      //     this.closeResult = `Closed with: ${result}`;
      //   },
      //   (reason) => {
      //     this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      //   }
      // );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return "by pressing ESC";
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return "by clicking on a backdrop";
    } else {
      return `with: ${reason}`;
    }
  }
}
