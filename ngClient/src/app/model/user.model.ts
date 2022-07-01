export interface User{
    id: string,
    username: string,
    roles: [
        {
            roleName: string
        }
    ]
}