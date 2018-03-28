import { User } from "./user";

export class Post {
    
    postID:number;
    user:User;
    message:string;
	likes:number;
	likedBy: number[]; //set of userIDs that have liked the post.
    image: Blob; //blob? s3?

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
		return this.postID;
	}

	public set $postID(value: number) {
		this.postID = value;
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
	public get $likes(): number {
		return this.likes;
	}

	public set $likes(value: number) {
		this.likes = value;
	}
 
	//likedBy
	public get $likedBy(): number[] {
		return this.likedBy;
	}

	public set $likedBy(value: number[]) {
		this.likedBy = value;
    }

    //image
	public get $image(): Blob {
		return this.image;
	}

	public set $image(value: Blob) {
		this.image = value;
	}
	
	//?? not sure if this is how we are doing it
	public addUserIDtoLikedBy(userID){
		this.likedBy.push(userID);
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