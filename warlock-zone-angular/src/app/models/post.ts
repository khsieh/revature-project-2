import { User } from "./user";

export class Post {
    
    postId:number;
    user:User;
    message:string;
	likes:string[];
	// likedBy: number[]; //set of userIDs that have liked the post.
    image: Blob; //blob? s3?
	position: number[];
    constructor( ) {}
    
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

	// ngOnInit(){
		
	// }

    //postID
	public get $postID(): number {
		return this.postId;
	}

	public set $postID(value: number) {
		this.postId = value;
    }
    
    //author
	public get $user(): User {
		return this.user;
	}

	public set $user(value: User) {
		this.user = value;
	}

    //content
	public get $message(): string {
		return this.message;
	}

	public set $message(value: string) {
		this.message = value;
	}
    
    //likes
	public get $likes(): string[] {
		return this.likes;
	}

	public set $likes(value: string[]) {
		this.likes = value;
	}
 
	// //likedBy
	// public get $likedBy(): number[] {
	// 	return this.likedBy;
	// }

	// public set $likedBy(value: number[]) {
	// 	this.likedBy = value;
    // }

    //image
	public get $image(): Blob {
		return this.image;
	}

	public set $image(value: Blob) {
		this.image = value;
	}
	
	public initPos(){
		this.position = [];
		this.position.push(Math.floor(Math.random() * 100));
		this.position.push(Math.floor(Math.random() * 100));
	}

	//image
	public get $pos(): number[] {
		return this.position;
	}

	public set $pos(value: number[]) {
		this.position = value;
	}

	public getJSON() {
		// let postJson = {'message':null, 'likes':null, 'image':null, 'user': null};
		// postJson.message = this.message;
		// postJson.likes = 5;
		// postJson.image = null;
		// postJson.user = this.user;
		return JSON.parse(JSON.stringify(this));
	}

}