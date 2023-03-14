import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Note } from "./note";
import { environment } from "src/environments/environment";




@Injectable({
    providedIn:'root'
})
export class NoteServices{
    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    private apiServerUrl = environment.apiBaseUrl;
    constructor(private http:HttpClient){}

    public getNotes(page:number,size:number):Observable<Array<Note>>{
        return this.http.get<Array<Note>>(`${this.apiServerUrl}/pageData?page=${page}&size=${size}`,{responseType: 'json'});
    }

    public getLogin():Observable<any>{
        return this.http.get(`http://localhost:8888/login`,{responseType: 'text'});
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