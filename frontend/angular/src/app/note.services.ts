import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Note } from "./note";
import { environment } from "src/environments/environment";




@Injectable({
    providedIn:'root'
})
export class NoteServices{
    private apiServerUrl = environment.apiBaseUrl;
    constructor(private http:HttpClient){}

    public getNotes(page:number,size:number):Observable<Note[]>{
        return this.http.get<Note[]>(`${this.apiServerUrl}/pageData?page=${page}&size=${size}`);
    }

    public addNote(note: Note):Observable<Note>{
        return this.http.post<Note>(`${this.apiServerUrl}/createNote`,note);
    }

    public editNotes(note: Note):Observable<Note>{
        return this.http.post<Note>(`${this.apiServerUrl}/EditNote`,note);
    }

    public deleteNotes(note: Note):Observable<Note>{
        return this.http.post<Note>(`${this.apiServerUrl}/DeleteNote`,note);
    }
}