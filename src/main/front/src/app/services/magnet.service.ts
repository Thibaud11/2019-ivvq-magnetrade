import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Magnet } from '../model/magnet';
import { Observable } from 'rxjs';

@Injectable()
export class MagnetService {

    private url: string;

    constructor(private http: HttpClient) {
        this.url = environment.api + '/magnet';
    }

    create(magnet: Magnet): Observable<Magnet> {
        return this.http.post<Magnet>(this.url + '/save', magnet);
    }

    edit(magnet: Magnet): Observable<Magnet> {
        return this.http.post<Magnet>(this.url + '/edit', magnet);
    }

    delete(id: number) {
        return this.http.delete(this.url + '/delete/' + id);
    }

    get(id: number) {
        return this.http.get(this.url + '/read/' + id);
    }

    getAll() {
        return this.http.get(this.url + '/all');
    }

}
