import { Magnet } from "./magnet";
import { Comment } from "./comment";

export abstract class AbstractPost {

    id: number;
    isActive: boolean;
    picture: string;
    text: string;
    creationDate: Date;
    magnets: Magnet[];
    comments: Comment[];

}
