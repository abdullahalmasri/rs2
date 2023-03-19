import { Label } from "./label";

export interface Note{
    id?:number;
    title:string;
    content:string;
    labels:Array<Label>;
}