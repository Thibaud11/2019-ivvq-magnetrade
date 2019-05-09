import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

/* *** Components *** */
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';

/* *** Services *** */
import { MagnetService } from './services/magnet.service';
import { CommentService } from './services/comment.service';
import { ProposalService } from './services/proposal.service';
import { RequestService } from './services/request.service';
import { UserService } from './services/user.service';

/* *** Primeng *** */
import { CardModule } from 'primeng/card';

/* *** Routes *** */
const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'create-post', component: CreatePostComponent }
];

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        CreatePostComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule.forRoot(routes, { useHash: true }),
        HttpClientModule,
        CardModule
    ],
    providers: [
        CommentService,
        MagnetService,
        ProposalService,
        RequestService,
        UserService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
