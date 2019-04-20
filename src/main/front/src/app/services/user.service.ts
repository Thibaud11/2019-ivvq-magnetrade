import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable()
export class UserService {

    private url: string;

    constructor(private http: HttpClient) {
        this.url = environment.api + '/user';
    }

    create(user: User): Observable<User> {
        return this.http.post<User>(this.url + '/create', user);
    }

    edit(user: User): Observable<User> {
        return this.http.post<User>(this.url + '/edit', user);
    }

    delete(id: number) {
        return this.http.delete(this.url + '/delete/' + id);
    }

    get(id: number): Observable<User> {
        return this.http.get<User>(this.url + '/' + id);
    }

    getAll(): Observable<User[]> {
        return this.http.get<User[]>(this.url + '/all');
    }

}
