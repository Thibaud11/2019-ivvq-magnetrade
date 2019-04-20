import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Comment } from '../model/comment';
import { Observable } from 'rxjs';

@Injectable()
export class CommentService {

    private url: string;

    constructor(private http: HttpClient) {
        this.url = environment.api + '/comment';
    }

    create(comment: Comment): Observable<Comment> {
        return this.http.post<Comment>(this.url + '/create', comment);
    }

    edit(comment: Comment): Observable<Comment> {
        return this.http.post<Comment>(this.url + '/edit', comment);
    }

    delete(id: number) {
        return this.http.delete(this.url + '/delete/' + id);
    }

    get(id: number): Observable<Comment> {
        return this.http.get<Comment>(this.url + '/' + id);
    }

    getAll(): Observable<Comment[]> {
        return this.http.get<Comment[]>(this.url + '/all');
    }

}
