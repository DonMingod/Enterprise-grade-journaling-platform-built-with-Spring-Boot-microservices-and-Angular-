export interface AuthResponseDto {
    id: number;
    username: string;
    email: string;
    token: string;
    roles: string[];
}
