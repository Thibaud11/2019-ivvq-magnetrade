import { User } from "./user";

export class Comment {

    id: number;
    isEdited: boolean;
    date: Date;
    text: string;
    commenter: User;

}
