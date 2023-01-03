export interface Profile {
    profileId: string,
    firstName: string,
    lastName: string,
    email: string,
    telephoneNumber: string,
    gender: string,
    dateOfBirth: Date,
    status:string,
    aboutMe: string,
    address: Address
}

export interface Date {
    day: number,
    month: number,
    year: number
}

export interface Address {
    street: string,
    city: string,
    province: string,
    country: string,
    postalCode: string,
    apartmentNumber: string
}