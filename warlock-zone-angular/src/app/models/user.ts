export class User {

	userID:number;
    username:string;
    password:string;
    profilePicture: string; //blob?? S3??
	firstName:string;
	lastName:string;
    email:string;
    
    constructor() {}
    /*
    constructor(
        $userID: number, 
        $username: string, 
        $password: string,
        $profilePicture: Blob, 
        $firstName: string, 
        $lastName: string, 
        $email: string) 
    {
		this.userID = $userID;
		this.username = $username;
        this.password = $password;
        this.profilePicture = $profilePicture;
		this.firstName = $firstName;
		this.lastName = $lastName;
		this.email = $email;
    }
    */
    public setAll(newU:User):void{
		this.$userID = newU.$userID;
		this.$username = newU.$username;
        this.$password = newU.$password;
        this.$firstName = newU.$firstName;
        this.$lastName = newU.$lastName;
		this.$email = newU.$email;
		this.$profilePicture = newU.$profilePicture;

    }

    //userID
	public get $userID(): number {
		return this.userID;
	}

	public set $userID(value: number) {
		this.userID = value;
	}

    //username
	public get $username(): string {
		return this.username;
	}

	public set $username(value: string) {
		this.username = value;
	}

    //password
	public get $password(): string {
		return this.password;
	}

	public set $password(value: string) {
		this.password = value;
	}

    public get $profilePicture(){
        return this.profilePicture
    }
    
    public set $profilePicture(value: string){
        this.profilePicture = value;
    }

    //firstname
	public get $firstName(): string {
		return this.firstName;
	}

	public set $firstName(value: string) {
		this.firstName = value;
    }
    
    //lastname
	public get $lastName(): string {
		return this.lastName;
	}

	public set $lastName(value: string) {
		this.lastName = value;
	}

    //email
	public get $email(): string {
		return this.email;
	}

	public set $email(value: string) {
		this.email = value;
    }
    
}