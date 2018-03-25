import { User } from "./user";

export class Post {
    
    postID:number;
    author:User;
    content:string;
    likes:number;
    picture: Blob; //blob? s3?

    constructor() {}
    
    /*
    constructor(
        $postID: number,
        $author: User,
        $content: string,
        $likes: number,
        $picture: Blob) 
    {
        this.postID = $postID;
        this.author = $author;
        this.content = $content;
        this.likes = $likes;
        this.picture = $picture;
    }
    */

    //postID
	public get $postID(): number {
		return this.postID;
	}

	public set $postID(value: number) {
		this.postID = value;
    }
    
    //author
	public get $author(): User {
		return this.author;
	}

	public set $author(value: User) {
		this.author = value;
	}

    //content
	public get $content(): string {
		return this.content;
	}

	public set $content(value: string) {
		this.content = value;
	}
    
    //likes
	public get $likes(): number {
		return this.likes;
	}

	public set $likes(value: number) {
		this.likes = value;
	}
 
    //image
	public get $picture(): Blob {
		return this.picture;
	}

	public set $picture(value: Blob) {
		this.picture = value;
	}
    
}